import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormProductService} from "./form-product.service";
import {Product} from "../shared/data-types/Product";

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  @Input() product: Product | undefined = undefined;

  createForm: FormGroup = this.formBuilder.group({
    name:[null, Validators.required],
    description:[null, Validators.required],
    targetMarkets:[null, Validators.required],
    technologies:[null, Validators.required]
  });
  constructor(private formService: FormProductService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    if(this.product != undefined){
      this.createForm.setValue({"name": this.product.productName});
      this.createForm.setValue({"description": this.product.description});
      this.createForm.setValue({"targetMarkets": this.product.targetMarketStack});
      this.createForm.setValue({"technologies": this.product.technologiesStack});
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
          console.log('response received')
          console.log(response)
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
      this.formService.update(product);
    }
  }

}
