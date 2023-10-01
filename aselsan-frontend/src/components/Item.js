import { Button, Card } from 'react-bootstrap';

const Item = ({ item, onBuyClick }) => {
    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={item.imgURL} />
            <Card.Body>
                <Card.Title>{item.name}</Card.Title>
                <Card.Text>
                    Price: {item.price} <br />
                    In Stock: {item.stock}
                </Card.Text>
                <Button variant="primary" onClick={() => onBuyClick()}>Buy</Button>
            </Card.Body>
        </Card >
    );
}

export default Item;