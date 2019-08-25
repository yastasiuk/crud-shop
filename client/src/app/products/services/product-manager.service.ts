import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Injectable({
  providedIn: 'root'
})

export class ProductManagerService {
    private productsUrl = '/api/products';

    constructor(private http: HttpClient) { }

    // Move to observables ()
    getProducts(params: { [key: string]: string | string[]}): Observable<Product[]> {
        return this.http.get<Product[]>(`${this.productsUrl}/list`, { params });
    }

    updateProduct(id: string, product: FormData): Observable<Product> {
        console.log('updateProduct()', product);
        if (!id) {
            console.error('updateProduct()', 'product.id is undefined');
        }
        return this.http.patch<Product>(`${this.productsUrl}/${id}`, product);
    }

    createProduct(product: FormData) {
        console.debug('createProduct()');
        return this.http.post<Product>(`${this.productsUrl}/create`, product);
    }

    deleteProduct(productId: string) {
        console.debug('deleteProduct()', productId);
        return this.http.delete(`${this.productsUrl}/${productId}`);
    }

    getProductById(productId: string) {
        console.debug('getProductById()', productId);
        return this.http.get<Product>(`${this.productsUrl}/${productId}`);
    }

    searchProductsByArticle(article: string) {
        return this.http.get<Product[]>(`${this.productsUrl}/list?article=${article}`);
    }
}
