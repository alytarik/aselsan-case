import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const Item = ({ item }) => {
    return (
        <Card style={{ width: '18rem' }}>
            <Card.Img variant="top" src={"https://picsum.photos/" + (300 + item.price)} />
            <Card.Body>
                <Card.Title>{item.name}</Card.Title>
                <Card.Text>
                    Price: {item.price} <br />
                    In Stock: {item.stock}
                </Card.Text>
                <Button variant="primary">Buy</Button>
            </Card.Body>
        </Card >
    );
}

export default Item;