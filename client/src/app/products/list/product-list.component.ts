import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/model/product';
import { ProductManagerService } from '../services/product-manager.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

    products: Product[];
    private searchQueryParams: any = {};

    constructor(private productManagerService: ProductManagerService, private route: ActivatedRoute) {}

    ngOnInit() {
        // TODO: use pipe!
        const query = this.route.snapshot.queryParamMap;
        if (query.get('name')) {
            this.searchQueryParams.name = query.get('name');
        }
        this.getProducts(this.searchQueryParams);
    }

    getProducts(query: object): void {
        this.productManagerService.getProducts(query)
            .subscribe(products => this.products = products);
    }

}
