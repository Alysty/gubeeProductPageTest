import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormProductService} from "./form-product.service";
import {Product} from "../shared/data-types/Product";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})
export class FormProductComponent implements OnInit {

  @Input() product: Product | undefined;

  createForm: FormGroup = this.formBuilder.group({
    name:[null, [Validators.required, Validators.minLength(3)]],
    description:[null, [Validators.required, Validators.minLength(5)]],
    targetMarkets:[null,  [Validators.required, Validators.minLength(3)]],
    technologies:[null, [Validators.required, Validators.minLength(3)]]
  });
  constructor(private formService: FormProductService,
              private formBuilder:FormBuilder,
              private activatedRoute:ActivatedRoute,
              private router:Router) { }

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
    if(this.createForm.valid){
      if(this.product == undefined){
        this.createProduct();
      }else{
        this.updateProduct();
      }
      this.router.navigate(['/']).catch()
    }else{
      alert("Invalid product")
    }
  }
  verifyValidAndTouched(field: string){
    return !this.createForm.get(field)?.valid && this.createForm.get(field)?.touched
  }
  validationErrors(field: string){
    return{
      'is-invalid': this.verifyValidAndTouched(field),
      'has-feedback': this.verifyValidAndTouched(field),
      'has-danger-custom': this.verifyValidAndTouched(field)
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
