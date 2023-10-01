import { Button, Card, Form } from 'react-bootstrap';
import { useState } from 'react';

// admin controller for item to set its price and stock without buy functionality

const ItemAdmin = ({ item, onSaveClick }) => {

    const [price, setPrice] = useState(item.price);
    const [stock, setStock] = useState(item.stock);

    const handlePriceChange = (e) => {
        setPrice(e.target.value);
    }

    const handleStockChange = (e) => {
        setStock(e.target.value);
    }

    const handleSaveClick = () => {
        item.price = price;
        item.stock = stock;
        onSaveClick();
    }

    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={item.imgURL} />
            <Card.Body>
                <Card.Title>{item.name}</Card.Title>
                <Card.Text>
                    Price: {item.price} ₺<br />
                    Stock: {item.stock}
                </Card.Text>
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Price</Form.Label>
                        <Form.Control type="number" placeholder="0" value={price} onChange={handlePriceChange} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Stock</Form.Label>
                        <Form.Control type="number" placeholder="0" value={stock} onChange={handleStockChange} />
                    </Form.Group>
                    <Button variant="primary" onClick={() => handleSaveClick()}>Save</Button>
                </Form>
            </Card.Body>
        </Card >
    );
}

export default ItemAdmin;