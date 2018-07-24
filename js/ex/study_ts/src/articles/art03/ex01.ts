interface ProductInCart {
	id: string
	amount: number
}
class CartModel {
	products: Record<string, ProductInCart> = {}
	error?: CartErrors
}
const cartState: ProductInCart = {
    id: '14',
    amount: 10,
}
enum ErrorsEnum {
    NetworkError = 'NetworkError',
    ServerError = 'ServerError',
}
type CartErrors = Partial<Record<ErrorsEnum, string>>