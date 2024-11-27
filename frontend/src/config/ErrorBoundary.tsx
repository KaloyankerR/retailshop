import React, { Component, ErrorInfo, ReactNode } from 'react';

interface ErrorBoundaryProps {
  children: ReactNode;
}

interface ErrorBoundaryState {
  hasError: boolean;
  errorMessage: string;
}

class ErrorBoundary extends Component<ErrorBoundaryProps, ErrorBoundaryState> {
  constructor(props: ErrorBoundaryProps) {
    super(props);
    this.state = {
      hasError: false,
      errorMessage: '',
    };
  }

  static getDerivedStateFromError(error: Error): ErrorBoundaryState {
    return { hasError: true, errorMessage: error.message };
  }

  componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    console.error('Error caught by ErrorBoundary:', error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      setTimeout(() => {
        this.setState({ hasError: false, errorMessage: '' });
        window.location.href = '/';
      }, 3000);

      return (
        <div className="error-message">
          <h1>Something went wrong!</h1>
          <p>{this.state.errorMessage}</p>
          <p>Redirecting to the home page...</p>
        </div>
      );
    }

    return this.props.children;
  }
}

export default ErrorBoundary;
