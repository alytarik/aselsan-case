import { Button, Card } from 'react-bootstrap';
import PropTypes from 'prop-types';

const Item = ({ item, onBuyClick }) => {
    return (
        <Card style={{ height: '30rem' }}>
            <Card.Img className='p-4' variant="top" src={item.imgURL} />
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

Item.propTypes = {
    item: PropTypes.shape({
        imgURL: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        stock: PropTypes.number.isRequired,
    }).isRequired,
    onBuyClick: PropTypes.func.isRequired,
};

export default Item;