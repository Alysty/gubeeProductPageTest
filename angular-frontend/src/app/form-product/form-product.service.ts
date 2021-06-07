import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../shared/data-types/Product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FormProductService {

  constructor(private http: HttpClient) { }

  create(product:Product) :Observable<any> {
    return this.http.post("http://localhost:8090/products", product);
  }
  update(product:Product) :Observable<any>  {
    return this.http.put("http://localhost:8090/products", product)
  }
}
