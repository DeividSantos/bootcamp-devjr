import { Injectable } from '@angular/core';
import { Product } from '../interfaces/Product';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts() : Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/products");
  }

  save(product:Product){
    return this.http.post<Product>("http://localhost:8080/products", product);
  }

  update(product:Product){
    return this.http.put<Product>(`http://localhost:8080/products/${product.id}`, product);
  }

  delete(product:Product){
    return this.http.delete<void>(`http://localhost:8080/products/${product.id}`);
  }

}
