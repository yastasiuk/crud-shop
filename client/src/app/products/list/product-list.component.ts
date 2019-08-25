import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Product } from 'src/app/model/product';
import { ProductManagerService } from '../services/product-manager.service';

interface ISearchQuery extends Map<string, string> {
    name?: string;
}

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

    products: Product[];
    searchQueryParams: ISearchQuery;

    constructor(private productManagerService: ProductManagerService, private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.queryParamMap.subscribe(queryParams => {
            this.searchQueryParams = this.constructQuery(queryParams);
            this.getProducts(this.searchQueryParams);
        });
    }

    constructQuery(queryParams: ParamMap) {
        const searchQueryParams: ISearchQuery = new Map();
        const name = queryParams.get('name');
        if (name) {
            searchQueryParams.set('name', name);
        }
        return searchQueryParams;
    }

    getProducts(query: ISearchQuery): void {
        const obj = {};
        query.forEach((value, key) => {
            obj[key] =  value;
        });
        this.productManagerService.getProducts(obj)
            .subscribe(products => this.products = products);
    }

}
