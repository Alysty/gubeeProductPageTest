import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormProductService} from "./form-product.service";

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  createForm: FormGroup = this.formBuilder.group({
    nameSearch:[null, Validators.required],
    targetMarketsSearch:[null, Validators.required],
    technologiesSearch:[null, Validators.required]
  });
  constructor(private formService: FormProductService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
  }
  createProduct(){
    if(this.createForm.dirty && this.createForm.valid){
      this.formService.save();
    }
  }
}
