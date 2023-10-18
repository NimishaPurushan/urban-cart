import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
}

interface ProductCardProps {
  product: Product;
}

const ProductCard = (props: ProductCardProps) => {
  if (!props.product) {
    return <div>Loading...</div>;
  }

  return (
    <Card sx={{ margin: "10px", marginColor: "yellow", maxWidth: 345 }}>
      <CardMedia
        sx={{ maxHeight: 80 }}
        image="download.png"
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.product.name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.product.description}
        </Typography>
        <Typography variant="body2" color="text.primary" fontWeight={12}>
          ${props.product.price}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Buy Now</Button>
        <Button size="small">Add to Card</Button>
      </CardActions>
    </Card>
  );
};

export default ProductCard;
