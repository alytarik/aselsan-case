import { Container, Col, Row, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';

import api from './api/axiosConfig';

import ItemAdmin from './components/ItemAdmin';

function Admin() {
    const [items, setItems] = useState([]);
    const [moneyCollected, setMoney] = useState(0);

    useEffect(() => {
        api.get('items/')
            .then(response => setItems(response.data))
            .catch(error => console.log(error));
    }, []);

    useEffect(() => {
        api.get('admin/machine')
            .then(response => setMoney(response.data.moneyCollected))
            .catch(error => console.log(error));
    }, []);

    const handleSaveClick = (item) => {
        api.put(`items/${item.id}`, item)
            .then(response => console.log(response))
            .catch(error => console.log(error));
    }

    const handleCollectMoneyClick = () => {
        api.get('admin/machine/collect')
            .then(response => {
                setMoney(response.data.moneyCollected);
                alert("Money collected!");
            })
            .catch(error => console.log(error));
    }

    const handleResetClick = () => {
        api.get('admin/machine/reset')
            .then(response => {
                setMoney(response.data.moneyCollected);
                alert("Machine has been reset!");
                window.location.reload();
            })
            .catch(error => console.log(error));
    }

    return (
        <div className="App">
            <Container>
                <h1>Vending Machine</h1>
                <br />
                <Row>
                    <h4>Money In The Machine: {moneyCollected} ₺</h4>
                    <div>
                        <Button className='m-2' onClick={() => handleCollectMoneyClick()}>Collect Money</Button>
                        <Button className='m-2' onClick={() => handleResetClick()}>Reset Machine</Button>

                    </div>
                </Row>

                <Row className="justify-content-md-center">
                    {items.map((item) => (
                        <Col key={item.id} xs={3}>
                            <ItemAdmin item={item} onSaveClick={() => handleSaveClick(item)} />
                        </Col>
                    ))}
                </Row>

            </Container>
        </div>
    );
}

export default Admin;
