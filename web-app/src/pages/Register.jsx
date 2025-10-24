import {
    Box,
    Button,
    Card,
    CardContent,
    TextField,
    Typography,
    Snackbar,
    Alert,
} from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../services/authenticationService";

export default function Register() {
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dob, setDob] = useState("");
    const [city, setCity] = useState("");

    const [snackBarOpen, setSnackBarOpen] = useState(false);
    const [snackBarMessage, setSnackBarMessage] = useState("");
    const [snackBarSeverity, setSnackBarSeverity] = useState("success");

    const handleCloseSnackBar = (event, reason) => {
        if (reason === "clickaway") {
            return;
        }

        setSnackBarOpen(false);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const payload = {
            username,
            password,
            email,
            firstName,
            lastName,
            dob,
            city,
        };

        try {
            const response = await register(payload);
            // expected success response: { code: 1000, result: { ... } }
            setSnackBarMessage("Registration successful. You can now login.");
            setSnackBarSeverity("success");
            setSnackBarOpen(true);

            // navigate to login after short delay to show snackbar
            setTimeout(() => {
                navigate("/login");
            }, 1000);
        } catch (error) {
            const message =
                error?.response?.data?.message || error?.message || "Registration failed";
            setSnackBarMessage(message);
            setSnackBarSeverity("error");
            setSnackBarOpen(true);
        }
    };

    return (
        <>
            <Snackbar
                open={snackBarOpen}
                onClose={handleCloseSnackBar}
                autoHideDuration={6000}
                anchorOrigin={{ vertical: "top", horizontal: "right" }}
            >
                <Alert
                    onClose={handleCloseSnackBar}
                    severity={snackBarSeverity}
                    variant="filled"
                    sx={{ width: "100%" }}
                >
                    {snackBarMessage}
                </Alert>
            </Snackbar>

            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                height="100vh"
                bgcolor={"#f0f2f5"}
            >
                <Card
                    sx={{
                        minWidth: 300,
                        maxWidth: 500,
                        boxShadow: 3,
                        borderRadius: 3,
                        padding: 4,
                    }}
                >
                    <CardContent>
                        <Typography variant="h5" component="h1" gutterBottom>
                            Create an account
                        </Typography>

                        <Box
                            component="form"
                            display="flex"
                            flexDirection="column"
                            alignItems="center"
                            justifyContent="center"
                            width="100%"
                            onSubmit={handleSubmit}
                        >
                            <TextField
                                label="Username"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />

                            <TextField
                                label="Password"
                                type="password"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />

                            <TextField
                                label="Email"
                                type="email"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />

                            <TextField
                                label="First name"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}
                            />

                            <TextField
                                label="Last name"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={lastName}
                                onChange={(e) => setLastName(e.target.value)}
                            />

                            <TextField
                                label="Date of birth"
                                type="date"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                InputLabelProps={{ shrink: true }}
                                value={dob}
                                onChange={(e) => setDob(e.target.value)}
                            />

                            <TextField
                                label="City"
                                variant="outlined"
                                fullWidth
                                margin="normal"
                                value={city}
                                onChange={(e) => setCity(e.target.value)}
                            />

                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                size="large"
                                fullWidth
                                sx={{ mt: "15px", mb: "10px" }}
                            >
                                Register
                            </Button>
                        </Box>
                    </CardContent>
                </Card>
            </Box>
        </>
    );
}
