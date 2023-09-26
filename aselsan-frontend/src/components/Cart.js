import React from 'react';

const Cart = ({ cart }) => {
    return (
        <div>
            <h1>Cart</h1>
            <ul>
                {cart.map((item) => (
                    <li key={item.id}> {item.name} </li>
                ))}
            </ul>
        </div>
    );
}

export default Cart;