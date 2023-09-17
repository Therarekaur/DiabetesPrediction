# Use the official Python 3.10.0 base image
FROM python:3.10.0

# Set the working directory inside the container
WORKDIR /app

# Copy the requirements file into the container
COPY requirements.txt .

# Install the application dependencies
RUN pip install -r requirements.txt

# Copy the rest of the application code into the container
COPY . .

# Specify the command to run when the container starts
CMD ["uvicorn", "diabetes_api:app", "--host", "0.0.0.0", "--port", "8000"]
