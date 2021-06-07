import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  createForm: FormGroup = this.formBuilder.group({
    nameSearch:[null],
    targetMarketsSearch:[null],
    technologiesSearch:[null]
  });
  constructor(private formBuilder:FormBuilder) { }

  ngOnInit(): void {
  }

}
