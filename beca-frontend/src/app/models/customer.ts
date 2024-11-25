import { Account } from '../models/account';

export interface Customer {
  id: number;
  firstName: string;
  lastName: string;
  accounts: Account[];
}
