import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormProductComponent } from './form-product.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FormProductRoutingModule} from "./form-product-routing.module";



@NgModule({
  declarations: [
    FormProductComponent
  ],
  imports: [
    CommonModule,
    FormProductRoutingModule,
    ReactiveFormsModule
  ]
})
export class FormProductModule { }
