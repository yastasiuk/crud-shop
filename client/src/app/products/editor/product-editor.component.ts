import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import { ProductManagerService } from 'src/app/products/services/product-manager.service';


@Component({
    selector: 'app-editor',
    templateUrl: './product-editor.component.html',
    styleUrls: ['./product-editor.component.scss']
})
export class ProductEditorComponent implements OnInit {
    productLoaded: Promise<boolean>;
    formEditor;
    product: Product;
    productImage: File;
    previewProductImage: string | ArrayBuffer | null;

    constructor(
        private formBuilder: FormBuilder,
        private productManagerService: ProductManagerService,
        private route: ActivatedRoute,
        private router: Router
    ) {

    }

    ngOnInit() {
        this.getProduct()
            .then((product: Product) => {
                console.log('SUCCESS:', product);
                this.product = product;
                this.formEditor = this.createFormGroup(product);
                this.productLoaded = Promise.resolve(true);
            })
            .catch(err => {
                console.log('ERROR:', err);
            });
    }

    getProduct() {
        return new Promise((res, rej) => {
            const id = this.route.snapshot.paramMap.get('id');
            if (id) {
                this.productManagerService.getProductById(id)
                    .subscribe(
                        product => res(product),
                        err => rej(err)
                    );
            } else {
                res(new Product());
            }
        });
    }

    createFormGroup(product: Product): FormGroup {
        return this.formBuilder.group(product);
    }

    handleImageChange(files: FileList) {
        console.log('handleImageChange', files);
        const reader = new FileReader();
        reader.addEventListener('load', () => {
          this.previewProductImage = reader.result;
        }, false);
        if (files[0]) {
          this.productImage = files[0];
          reader.readAsDataURL(this.productImage);
        }
    }

    onSubmit(product: Product) {
        console.log('onSubmit()', product);
        const formData = new FormData();
        formData.append('name', product.name);
        formData.append('series', product.series);
        formData.append('manufacturer', product.manufacturer);
        formData.append('vendorCode', product.vendorCode);
        if (this.productImage) {
            console.log('good', this.productImage);
            formData.append('image', this.productImage, this.productImage.name);
        } else {
            formData.append('image', '');
        }
        console.log('formData', formData);
        console.log('this.productImage', this.productImage);
        let productReq;
        const isANewProduct = !product.id;
        if (!isANewProduct) {
            productReq = this.productManagerService.updateProduct(product.id, formData);
        } else {
            productReq = this.productManagerService.createProduct(formData);
        }
        productReq.subscribe(
                (productRes: Product) => {
                    console.log('productRes', isANewProduct, productRes);
                    if (isANewProduct) {
                        // TODO: add smooth change
                        this.router.navigateByUrl(`product/${productRes.id}/edit`);
                    }
                },
                (error) => console.log('ERROR product', error)
            );
    }

    onResetForm(event: Event) {
        console.log('onResetForm()');
        event.preventDefault();
        this.formEditor.reset(this.product);
    }

    onDeleteProduct(event: Event) {
        console.log('onDeleteProduct()');
        event.preventDefault();
        this.productManagerService.deleteProduct(this.product.id).subscribe(
            () => this.router.navigateByUrl('/products'),
        );
    }
}
