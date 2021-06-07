import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormProductService} from "./form-product.service";
import {Product} from "../shared/data-types/Product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  @Input() product: Product | undefined;

  createForm: FormGroup = this.formBuilder.group({
    name:[null, Validators.required],
    description:[null, Validators.required],
    targetMarkets:[null, Validators.required],
    technologies:[null, Validators.required]
  });
  constructor(private formService: FormProductService,
              private formBuilder:FormBuilder,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      if(params.get('product') != null){
        this.product = JSON.parse((<string>params.get('product')).toString());
      }
    });
    if(this.product != undefined){
      this.createForm.setValue({
        "name": this.product.productName,
        "description": this.product.description,
        "targetMarkets": this.product.targetMarketStack,
        "technologies": this.product.technologiesStack});
    }
  }

  save(){
    if(this.product == undefined){
      this.createProduct();
    }else{
      this.updateProduct();
    }
  }
  createProduct(){
    if(this.createForm.dirty && this.createForm.valid){

      let product = Product.createProductObject(undefined,
        this.createForm.get('name')?.value,
        this.createForm.get('description')?.value,
        this.createForm.get('targetMarkets')?.value,
        this.createForm.get('technologies')?.value)
      this.formService.create(product).subscribe(

        (response) => {
        },
        (error) => {
          alert(error);
          console.error('Request failed with error')
        },
        () => {
        });
    }
  }
  updateProduct(){
    if(this.createForm.dirty && this.createForm.valid){
      // @ts-ignore
      let product = Product.createProductObject(this.product.id,
        this.createForm.get('name')?.value,
        this.createForm.get('description')?.value,
        this.createForm.get('targetMarkets')?.value,
        this.createForm.get('technologies')?.value)
      this.formService.update(product).subscribe(
        (response) => {
        },
        (error) => {
          alert(error);
          console.error('Request failed with error')
        },
        () => {
        });
    }
  }

}
