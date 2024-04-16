import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/interfaces/Category';
import { Product } from 'src/app/interfaces/Product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, OnChanges {

  @Input()
  categories : Category [] = [];

  @Input()
  product : Product = {} as Product;

  @Output()
  saveEmitter = new EventEmitter();

  formGroupProduct: FormGroup;
  
  constructor(private formBuilder: FormBuilder) {
    this.formGroupProduct = this.formBuilder.group({
      id : {value:null, disabled:true},
      name : ['', [Validators.required, Validators.minLength(3)]],
      description : ['',[Validators.required]],
      category : ['',[Validators.required]],
      price : ['',[Validators.required]],
      newProduct : [''],
      promotion : ['']
    });
  }

  ngOnChanges(): void {
    if(this.product.id){
      this.formGroupProduct.setValue(this.product);
    }
  }

  ngOnInit(): void {
  }

  save(){
    if(this.formGroupProduct.valid){
      Object.assign(this.product, this.formGroupProduct.value);
      this.saveEmitter.emit(true);
    }
  }

  cancel(){
    this.saveEmitter.emit(false);
  }

  selectedCategory(category1: Category, category2 : Category){
    return category1 && category2 ? category1.id === category2.id : false;
  }

  get pfgName() {return this.formGroupProduct.get("name")}
  get pfgDescription() {return this.formGroupProduct.get("description")}
  get pfgCategory() {return this.formGroupProduct.get("category")}
  get pfgPrice() {return this.formGroupProduct.get("price")}

}
