import { Card, Button, Container, Row, Col } from 'react-bootstrap'
import PropTypes from 'prop-types'

const Cart = ({ cart, onRefundClick, onFinishClick }) => {
    //display items in a scrollable list with a finish button at the bottom

    return (
        <Card style={{ width: '18rem' }}>
            <Card.Body>
                <Card.Title>Bought Items</Card.Title>
                <Card.Text>
                    {cart.length === 0 && 'No items are bought yet!'}
                    {cart.map((item, idx) => (
                        <Container key={idx}>
                            <Row>
                                <Col>{item.name}</Col>
                                <Col>{item.price} ₺</Col>
                                <Col><Button variant='danger' onClick={() => onRefundClick(idx)}>Refund</Button></Col>
                            </Row>
                        </Container>
                    ))}
                </Card.Text>
                {
                    cart.length !== 0 &&
                    <Button variant='success' onClick={() => onFinishClick()}>Finish</Button>
                }
            </Card.Body>
        </Card>
    );
}

Cart.propTypes = {
    cart: PropTypes.arrayOf(PropTypes.shape({
        imgURL: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        stock: PropTypes.number.isRequired,
    })).isRequired,
    onRefundClick: PropTypes.func.isRequired,
    onFinishClick: PropTypes.func.isRequired,
}

export default Cart;