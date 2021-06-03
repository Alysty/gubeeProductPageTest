export class Product{
  private _id: string;
  private _name: string;
  private _description: string;
  private _targetMarket: string[];
  private _technologies: string[];


  constructor(id: string, name: string, description: string, targetMarket: string[], technologies: string[]) {
    this._id = id;
    this._name = name;
    this._description = description;
    this._targetMarket = targetMarket;
    this._technologies = technologies;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get targetMarket(): string[] {
    return this._targetMarket;
  }

  set targetMarket(value: string[]) {
    this._targetMarket = value;
  }

  get technologies(): string[] {
    return this._technologies;
  }

  set technologies(value: string[]) {
    this._technologies = value;
  }
}
