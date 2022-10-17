import { OrderLine } from "./orderLine.model";
import { OrderStatus } from "./orderStatus.model";

export interface Order {
    id: Number,
    deliveryAddress: String,
    deliveryPostalCode: String,
    deliveryCity: String,
    deliveryPerson: String,
    idSeller: Number,
    products: Array<OrderLine>,
    status: OrderStatus,
    createdAt: Date,
    expectedDeliveryDate: Date,
    cost: Number
}