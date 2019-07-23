import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Injectable({
  providedIn: 'root'
})

export class ProductManagerService {
    private productsUrl = '/api/greeting';

    constructor(private http: HttpClient) { }

    // Move to observables ()
    getProducts(): Observable<Product[]> {
        return this.http.get<Product[]>(this.productsUrl);
    }
}
