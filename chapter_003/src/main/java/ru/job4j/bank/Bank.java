package ru.job4j.bank;

import java.util.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    private Map<User, List<Account>> mapUser = new HashMap<>();

    public Map<User, List<Account>> getMapUser() {
        return mapUser;
    }

    /**
     * Add an user to the collection.
     *
     * @param user - User.
     */
    public void addUser(User user) {
        mapUser.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Remove an user from the collection.
     *
     * @param user - User.
     */
    public void deleteUser(User user) {
        mapUser.remove(user);
    }

    /**
     * Add an account for the user in the collection.
     *
     * @param passport - User's passport.
     * @param account  - User's account.
     */
    public void addAccountToUser(String passport, Account account) {
        Optional<User> findUser = findUserOnPasport(passport);
        if (findUser.isPresent()) {
            mapUser.get(findUser.get()).add(account);
        }
    }

    /**
     * Remove an account for the user in the collection.
     *
     * @param passport - User's passport.
     * @param account  - User's account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        Optional<User> findUser = findUserOnPasport(passport);
        if (findUser.isPresent()) {
            User user = findUser.get();
            mapUser.get(user).remove(account);
        }
    }

    /**
     * Get account's collection from user.
     *
     * @param passport - Users's passport.
     * @return - Account's collection
     */
    public List<Account> getAccounts(String passport) {
        List<Account> result = null;
        Optional<User> findUser = findUserOnPasport(passport);
        if (findUser.isPresent()) {
            User user = findUser.get();
            result = mapUser.get(user);
        }
        return result;
    }

    /**
     * Transfer of maney from one account to another.
     *
     * @param srcPassport  - first user passport.
     * @param srcRequisite - first requisite account.
     * @param destPassport - second user passport.
     * @param dstRequisite - second requisite account.
     * @param amount       - amount's money.
     * @return - result of transfer.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        Account src;
        Account dest;
        boolean result = false;
        src = findAccountOnRequisite(srcRequisite, srcPassport);
        dest = findAccountOnRequisite(dstRequisite, destPassport);
        if (src != null && dest != null && src.getValue() > amount) {
            int number = (int) (src.getValue() - amount);
            src.setValue(number);
            int number1 = (int) (dest.getValue() + amount);
            dest.setValue(number1);
            result = true;
        }
        return result;
    }

    /**
     * @param passport - user passport.
     * @return - user.
     */
    private Optional<User> findUserOnPasport(String passport) {
        Optional<User> result = Optional.empty();
        for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
            if (entry.getKey().getPasport().equals(passport)) {
                result = Optional.of(entry.getKey());
                break;
            }
        }
        return result;
    }

    /**
     * @param requisite - requisite account.
     * @param passport  - user passport.
     * @return - account.
     */
    private Account findAccountOnRequisite(String requisite, String passport) {
        Account result = null;
        Optional<User> findUser = findUserOnPasport(passport);
        if (findUser.isPresent()) {
            User user = findUser.get();
            for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
                if (entry.getKey().equals(user)) {
                    for (Account account : entry.getValue()) {
                        if (account.getRequisites().equals(requisite)) {
                            result = account;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}