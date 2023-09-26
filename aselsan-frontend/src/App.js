import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Navbar from 'react-bootstrap/Navbar';
import Button from 'react-bootstrap/Button';

import Item from './components/Item';
import axios from 'axios';

import { useState, useEffect } from 'react';

function App() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/items/')
            .then(response => setItems(response.data))
            .catch(error => console.log(error));
    }, []);

    return (
        <div className="App">
            <Navbar expand='lg'>
                <Navbar.Brand className='pl-8'>My App</Navbar.Brand>
            </Navbar>

            <Container fluid>
                <Row>
                    <p>
                        Your balance: 0
                    </p>
                </Row>
                <Row>
                    <Col>Insert Money:</Col>
                    {[1, 5, 10, 20].map(item => <Col key={item}> <Button>{item} ₺</Button></Col>)}
                </Row>
                <Row className='bg-dark p-2'>
                    {items.map(item => <Col key={item.id} xs={12} md={4}><Item item={item} /></Col>)}
                </Row>
            </Container>
        </div>
    );
}

export default App;
