import { Container, Col, Row, ButtonGroup, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';

import api from './api/axiosConfig'

import Item from './components/Item';
import Cart from './components/Cart';
import OrderModal from './components/OrderModal';

function App() {
    const [items, setItems] = useState([]);
    const [itemsInCart, setItemsInCart] = useState([]);
    const [balance, setBalance] = useState(0);
    const [showOrderModal, setShowOrderModal] = useState(false);
    const [orderDetails, setOrderDetails] = useState({ items: [], change: 0 });

    const coins = [1, 5, 10, 20];

    useEffect(() => {
        api.get('items/')
            .then(response => setItems(response.data))
            .catch(error => console.log(error));
    }, []);

    const handleCoinClick = (amount) => {
        setBalance(balance + amount);
    }

    const handleBuyClick = (item) => {
        if (balance < item.price) {
            alert("Not enough balance!");
            return;
        }
        if (item.stock <= 0) {
            alert("There is no items left!");
            return;
        }
        setBalance(balance - item.price);
        item.stock -= 1;
        setItems([...items]);
        setItemsInCart([item, ...itemsInCart]);
    }

    const handleRefundClick = (idx) => {
        const refundItem = itemsInCart[idx]
        setBalance(balance + refundItem.price);
        refundItem.stock += 1;
        setItems([...items]);
        setItemsInCart(itemsInCart.filter((item, index) => index !== idx));
    }

    const handleFinishClick = () => {
        let orderItems = [];
        itemsInCart.forEach((item) => {
            const foundItem = orderItems.find((orderItem) => orderItem.id === item.id);
            if (foundItem) {
                foundItem.stock += 1;
            } else {
                orderItems.push({ ...item, stock: 1 });
            }
        });

        api.post('orders/', { items: orderItems })
            .then(() => {
                setOrderDetails({ items: orderItems, change: balance });
                setBalance(0);
                setItemsInCart([]);
                setShowOrderModal(true);
            })
            .catch(error => console.log(error));


    }

    return (
        <div className="App">
            <Container>
                <h1>Vending Machine</h1>
                <br />
                <Row>
                    <h4>Your Balance: {balance} ₺</h4>
                    <h4>Insert Coins:
                        <ButtonGroup className='mx-2'>
                            {coins.map((coin) => (<Button key={coin} onClick={() => handleCoinClick(coin)}>{coin} ₺</Button>))}
                        </ButtonGroup>
                    </h4>
                </Row>
                <br />

                <Row className="justify-content-md-center" >
                    {items.map((item) => (
                        <Col key={item.id} xs={3}>
                            <Item item={item} onBuyClick={() => handleBuyClick(item)} />
                        </Col>
                    ))}
                    <Col style={{ height: '30rem' }}> <Cart cart={itemsInCart} onRefundClick={(item) => handleRefundClick(item)} onFinishClick={() => handleFinishClick()} /> </Col>
                </Row>
            </Container>

            <OrderModal showModal={showOrderModal} setShowModal={(val) => setShowOrderModal(val)} orderDetails={orderDetails} />

        </div >
    );
}

export default App;
