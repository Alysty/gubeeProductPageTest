import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../shared/data-types/Product";

@Injectable({
  providedIn: 'root'
})
export class ListProductService {
  readonly ROOT_URL = "http://localhost:8090/products";



  constructor(private http: HttpClient) { }

  getProducts(name:string, targetMarkets:string[], technologies: string[]):Observable<any> {

    let httpParams = new HttpParams()
      .set('name', name)
      .set('markets',targetMarkets.toString())
      .set('technologies', technologies.toString());

    return this.http.get(this.ROOT_URL,
      {
        params:httpParams
    });

  }
}
