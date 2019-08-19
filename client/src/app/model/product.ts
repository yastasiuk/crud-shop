export class Product {
    id?: string;
    name: string;
    series: string;
    manufacturer: string;
    vendorCode: string;
    /** base64 string */
    imageIds: string[];

    constructor() {
        this.id = null;
        this.name = '';
        this.series = '';
        this.manufacturer = '';
        this.vendorCode = '';
        this.imageIds = [];
    }
}
