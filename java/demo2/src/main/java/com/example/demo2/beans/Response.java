package com.example.demo2.beans;
public class Response<T> {
        private String status;
        private String message;
        private T data;

        public Response() {
        }
        public Response(String status, String message) {
            this.status = status;
            this.message = message;
        }
        public Response(String status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
        public Response(ResponseCode status, T data) {
            this.status = status.toString();
            this.message = status.getCnMsg();
            this.data = data;
        }

    public Response(ResponseCode status) {
        this.status = status.toString();
        this.message = status.getMessage();
    }

    public Response(ResponseCode status, String message, T data) {
            this.status = status.toString();
            this.message = status.getCnMsg();
            this.data = data;
        }
        public String getStatus() {
            return this.status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public void setStatus(ResponseCode status) {
            this.status =status.toString();
            this.message=status.getMessage();
        }
        public String getMessage() {
            return this.message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public T getData() {
            return this.data;
        }
        public void setData(T data) {
            this.data = data;
        }

    }

