package com.auth.service;

import com.auth.dto.AccountDTO;
import com.auth.model.Account;
import com.auth.repository.AccountRepository;
import com.auth.security.service.UserDetailsImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    private ModelMapper modelMapper;
    @Value("${passwordSalt.length}")
    int length;
    @Value("${passwordSalt.hasLetter}")
    boolean hasLetter;
    @Value("${passwordSalt.hasNumber}")
    boolean hasNumber;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AccountRepository repository;


    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String generatePassWordSalt() {
        String generatedString = RandomStringUtils.random(length, hasLetter, hasNumber);
        return generatedString;
    }

    @Override
    @Transactional
    public String getPasswordsaltByUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery("Account.getPasswordSaltByUsername");
            query.setParameter("username", username);
            String passwordSalt = (String) query.getResultList().get(0);
            return null != passwordSalt ? passwordSalt : "";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountDTO toDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }


    @Override
    public List<AccountDTO> getAll() {
        List<Account> list = repository.findAll();
        return convertBoToDto(list);
    }

    @Override
    public List<AccountDTO> findAllByUsernameContaining(String username, Pageable pageable) {
        List<Account> list = repository.findAllByUsernameContaining(username.trim(), pageable);
        List<AccountDTO> result = new ArrayList<>();
        if (null != list && !list.isEmpty()) {
            AccountDTO dto = new AccountDTO();
            for (Account bo : list) {
                if (bo.getId().equals(getCurrentUser().getId())) {
                    continue;
                }
                dto.setId(bo.getId());
                dto.setUsername(bo.getUsername());
                dto.setAvatar(bo.getAvatar());
                result.add(dto);
                dto = new AccountDTO();
            }
        }
        return result;
    }

    @Override
    public List<AccountDTO> findAll(Pageable pageable) {
        List<Account> list = repository.findAllByUsernameContaining("", pageable);
        return convertBoToDto(list);
    }

    @Override
    public AccountDTO getCurrentUser() {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDTO dto = new AccountDTO();
        dto.setId(userPrincipal.getId());
        dto.setUsername(userPrincipal.getUsername());
        return dto;
    }

    @Override
    public Account addNew(Account account) {
        if (account != null)
            return repository.save(account);
        return null;
    }

    private List<AccountDTO> convertBoToDto(List<Account> bos) {
        List<AccountDTO> result = new ArrayList<>();
        if (null != bos && !bos.isEmpty()) {
            result = Arrays.asList(modelMapper.map(bos, AccountDTO[].class));

        }
        return result;
    }

}
