import { Component, OnInit } from '@angular/core';
import {Product} from "../shared/data-types/Product";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  productList : Product[] = [];

  constructor() { }


  ngOnInit(): void {
  }

}
