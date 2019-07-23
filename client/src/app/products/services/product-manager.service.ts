import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ProductManagerService {

    constructor(private httpClient: HttpClient) {

    }
}
