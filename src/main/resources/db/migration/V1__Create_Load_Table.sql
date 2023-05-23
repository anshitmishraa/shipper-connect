-- V1__Create_Load_Table.sql

-- Create the table for storing load details
CREATE TABLE load (
  id SERIAL PRIMARY KEY,
  loading_point VARCHAR(255) NOT NULL,
  unloading_point VARCHAR(255) NOT NULL,
  product_type VARCHAR(255) NOT NULL,
  truck_type VARCHAR(255) NOT NULL,
  no_of_trucks INTEGER NOT NULL,
  weight DECIMAL(10, 2) NOT NULL,
  comment VARCHAR(255),
  shipper_id UUID NOT NULL,
  load_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add an index on shipper_id for faster queries
CREATE INDEX idx_load_shipper_id ON load (shipper_id);
