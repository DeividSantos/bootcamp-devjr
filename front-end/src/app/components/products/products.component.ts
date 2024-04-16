import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Category } from 'src/app/interfaces/Category';
import { Product } from 'src/app/interfaces/Product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  categories: Category[] = [];

  product: Product = {} as Product;
  deleteProduct: Product = {} as Product;
  products: Product[] = [];

  showForm: boolean = false;
  isEditing: boolean = false;

  constructor(private categoryService: CategoryService,
    private productService: ProductService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
  }

  loadProducts() {
    this.productService.getProducts().subscribe({
      next: data => { this.products = data }
    })
  }

  loadCategories() {
    this.categoryService.getCategories().subscribe({
      next: data => { this.categories = data }
    });
  }

  saveProduct(save: boolean) {
    if (save) {
      if (this.isEditing) {
        this.productService.update(this.product).subscribe();
      }
      else {
        this.productService.save(this.product).subscribe({
          next: data => {
            this.products.push(data);
          }
        });
      }
    }

    this.product = {} as Product;
    this.showForm = false;
    this.isEditing = false;
  }

  create() {
    this.showForm = true;
  }

  edit(product: Product) {
    this.product = product;
    this.showForm = true;
    this.isEditing = true;
  }

  delete(modal: any, product: Product) {
    this.deleteProduct = product;
    this.modalService.open(modal).result.then(
      (confirm) => {
        if (confirm) {
          this.productService.delete(product).subscribe({
            next: () => {
              this.products = this.products.filter(p => p.id !== product.id);
            }
          });
        }
      }
    );
  }

}
