import {Optional} from "@angular/core";

export class Product{

  private _id?: string;
  private _productName: string;
  private _description: string;
  private _targetMarketStack: string[];
  private _technologiesStack: string[];


  constructor( id: string | undefined, productName: string, description: string, targetMarketStack: string[], technologiesStack: string[]) {
    this._id = id;
    this._productName = productName;
    this._description = description;
    this._targetMarketStack = targetMarketStack;
    this._technologiesStack = technologiesStack;
  }
  get id(): string | undefined {
    return this._id;
  }

  set id(value: string | undefined) {
    this._id = value;
  }

  get productName(): string {
    return this._productName;
  }

  set productName(value: string) {
    this._productName = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get targetMarketStack(): string[] {
    return this._targetMarketStack;
  }

  set targetMarketStack(value: string[]) {
    this._targetMarketStack = value;
  }

  get technologiesStack(): string[] {
    return this._technologiesStack;
  }

  set technologiesStack(value: string[]) {
    this._technologiesStack = value;
  }
}
