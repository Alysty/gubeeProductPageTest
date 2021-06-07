import {Optional} from "@angular/core";

export class Product{

  public id?: string;
  public productName: string;
  public description: string;
  public targetMarketStack: string[];
  public technologiesStack: string[];


  constructor( id: string | undefined, productName: string, description: string, targetMarketStack: string[], technologiesStack: string[]) {
    this.id = id;
    this.productName = productName;
    this.description = description;
    this.targetMarketStack = targetMarketStack;
    this.technologiesStack = technologiesStack;
  }

  public static createProductObject(id: string | undefined, productName: string, description: string, targetMarketStack: string, technologiesStack: string) : Product{
    let targetMarketStackTemp = targetMarketStack.split(',');
    let technologiesStackTemp = technologiesStack.split(',');
    return new Product(id, productName, description, targetMarketStackTemp, technologiesStackTemp);
  }
}
