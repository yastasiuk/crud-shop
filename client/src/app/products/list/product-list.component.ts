import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { ProductManagerService } from '../services/product-manager.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
    products: Product[];

    constructor(private productManagerService: ProductManagerService) { }

    ngOnInit() {
        this.getProducts();
    }

    getProducts(): void {
        this.productManagerService.getProducts()
            .subscribe(products => this.products = products);
    }

}
