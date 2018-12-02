class Error:
    def __init__(self, error_string):
        self.error = error_string

    def serialize(self):
        return {'error': self.error}


