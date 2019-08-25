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
    searchQueryParams: any = {};

    constructor(private productManagerService: ProductManagerService, private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.queryParamMap.subscribe(queryParams => {
            this.searchQueryParams.name = queryParams.get('name');
            this.getProducts(this.searchQueryParams);
        });
    }

    getProducts(query: object): void {
        this.productManagerService.getProducts(query)
            .subscribe(products => this.products = products);
    }

}
