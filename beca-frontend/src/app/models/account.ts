import { Transaction } from '../models/transaction';

export interface Account {
  id: number;
  name: string;
  total: number;
  transactions: Transaction[];
}
