import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Button from 'react-bootstrap/Button';

import Item from './components/Item';
import axios from 'axios';

import { useState, useEffect } from 'react';
import Cart from './components/Cart';

function App() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/items/')
            .then(response => setItems(response.data))
            .catch(error => console.log(error));
    }, []);

    return (
        <div className="App">
            <Container>
                <h1>Vending Machine</h1>
                <br />
                <Row>
                    <h4>Your Balance: 0 units</h4>
                    <h4>Insert Coins:
                        <ButtonGroup className='mx-2'>
                            <Button variant="success">1 ₺</Button>
                            <Button variant="success">2 ₺</Button>
                            <Button variant="success">5 ₺</Button>
                            <Button variant="success">10 ₺</Button>
                        </ButtonGroup>
                    </h4>
                </Row>

                <Row className="justify-content-md-center">
                    {items.map((item) => (
                        <Col key={item.id} xs={3}>
                            <Item item={item} />
                        </Col>
                    ))}
                    <Col> <Cart cart={items} /> </Col>
                </Row>

            </Container>
        </div>
    );
}

export default App;
