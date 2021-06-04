import { Component, OnInit } from '@angular/core';
import {Product} from "../shared/data-types/Product";
import {ListProductService} from "./list-product.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  // @ts-ignore
  searchForm: FormGroup;

  loading: boolean = false;

  productList : Product[] = [];

  nameSearch: string = "";

  errorMessage: string = "";

  targetMarketsSearch: string[] = [];

  technologiesSearch: string[] = [];

  constructor(private productService:ListProductService, private formBuilder:FormBuilder) { }


  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      nameSearch:[null],
      targetMarketsSearch:[null],
      technologiesSearch:[null]
    });
    this.getProducts();
  }

  public getProducts(){
    this.productService.getProducts(this.nameSearch, this.targetMarketsSearch, this.technologiesSearch)
      .subscribe(
        (response) => {                           //next() callback
          console.log('response received')
          this.productList = response;
        },
        (error) => {                              //error() callback
          console.error('Request failed with error')
          this.errorMessage = error;
          this.loading = false;
        },
        () => {                                   //complete() callback
          this.loading = false;
        })
  }

}
