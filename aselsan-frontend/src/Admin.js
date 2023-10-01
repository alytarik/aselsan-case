import { Container, Col, Row } from 'react-bootstrap';
import { useState, useEffect } from 'react';

import api from './api/axiosConfig';

import ItemAdmin from './components/ItemAdmin';

function Admin() {
    const [items, setItems] = useState([]);
    // const [balance, setBalance] = useState(0);

    useEffect(() => {
        api.get('items/')
            .then(response => setItems(response.data))
            .catch(error => console.log(error));
    }, []);

    const handleSaveClick = (item) => {
        api.put(`items/${item.id}`, item)
            .then(response => console.log(response))
            .catch(error => console.log(error));
    }

    return (
        <div className="App">
            <Container>
                <h1>Vending Machine</h1>
                <br />
                <Row>
                    <h4>Money Collected: 0 ₺</h4>
                    <h4>Insert Coins:
                    </h4>
                </Row>

                <Row className="justify-content-md-center">
                    {items.map((item) => (
                        <Col key={item.id} xs={3}>
                            <ItemAdmin item={item} onSaveClick={handleSaveClick} />
                        </Col>
                    ))}
                </Row>

            </Container>
        </div>
    );
}

export default Admin;
