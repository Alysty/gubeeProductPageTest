import { Component, OnInit } from '@angular/core';
import {Product} from "../shared/data-types/Product";
import {ListProductService} from "./list-product.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {


  searchForm: FormGroup = this.formBuilder.group({
    nameSearch:[''],
    targetMarketsSearch:[''],
    technologiesSearch:['']
  });

  loading: boolean = false;

  productList : Product[] = [];

  nameSearch: string = "";

  errorMessage: string = "";

  targetMarketsSearch: string[] = [];

  technologiesSearch: string[] = [];

  constructor(private productService:ListProductService, private formBuilder:FormBuilder) { }


  ngOnInit(): void {
    this.getProducts();
  }


  public setProductSearch(){
    if( this.searchForm.get('nameSearch')?.value != null) {
      this.nameSearch = this.searchForm.get('nameSearch')?.value;
    }else{
      this.nameSearch = "";
    }
    if (this.searchForm.get('targetMarketsSearch')?.value != null){
      this.targetMarketsSearch = this.searchForm.get('targetMarketsSearch')?.value.split(',');
    }
    if (this.searchForm.get('technologiesSearch')?.value != null) {
      this.technologiesSearch = this.searchForm.get('technologiesSearch')?.value.split(',');
    }

  }
  public getProducts(){
    this.setProductSearch();
    this.productService.getProducts(this.nameSearch, this.targetMarketsSearch, this.technologiesSearch)
      .subscribe(
        (response) => {
          console.log('response received')
          this.productList = response;
        },
        (error) => {
          console.error('Request failed with error')
          this.errorMessage = error;
          this.productList = [];
          this.loading = false;
        },
        () => {
          this.loading = false;
        })
  }
  public deleteProduct(id:string | undefined){
    this.productService.deleteProduct(id)
      .subscribe(
        (response)=>{
          this.getProducts();
        },
        (error) => {
          console.error('Request failed with error')
          this.errorMessage = error;
          this.loading = false;
        }
      )
  }
}
