export interface User {
    id: number;
    name: string;
    surname: string;
    email: string;
    role: string;
    status: string;
    createdAt: string;
    password: string;
  }

export interface Subject {
    id: number,
    name: string,
    description: string,
    user: User
}