import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductComponent } from './list-product.component';
import { ListProductRoutingModule } from "./list-product-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { ListProductService } from "./list-product.service";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    ListProductComponent
  ],
  imports: [
    CommonModule,
    ListProductRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers:[ListProductService]
})
export class ListProductModule { }
