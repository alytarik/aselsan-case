import { Container, Col, Row, ButtonGroup, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';

import axios from 'axios';

import Item from './components/Item';
import Cart from './components/Cart';



function App() {
    const [items, setItems] = useState([]);
    const [boughtItems, setBoughtItems] = useState([]);
    const [balance, setBalance] = useState(0);

    const coins = [1, 5, 10, 20];

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/items/')
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
        setBalance(balance - item.price);
        item.stock -= 1;
        setItems([...items]);
        setBoughtItems([item, ...boughtItems]);
    }

    const handleRefundClick = (idx) => {
        const refundItem = boughtItems[idx]
        setBalance(balance + refundItem.price);
        refundItem.stock += 1;
        setItems([...items]);
        setBoughtItems(boughtItems.filter((item, index) => index !== idx));
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

                <Row className="justify-content-md-center">
                    {items.map((item) => (
                        <Col key={item.id} xs={3}>
                            <Item item={item} onBuyClick={() => handleBuyClick(item)} />
                        </Col>
                    ))}
                    <Col> <Cart cart={boughtItems} onRefundClick={(item) => handleRefundClick(item)} /> </Col>
                </Row>

            </Container>
        </div>
    );
}

export default App;
