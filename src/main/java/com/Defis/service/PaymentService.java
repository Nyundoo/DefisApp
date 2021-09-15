package com.Defis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Defis.domain.Payment;
import com.Defis.exception.PaymentNotFoundException;
import com.Defis.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	public static final int PAYMENTS_PER_PAGE = 15;

	@Autowired
	private PaymentRepository paymentRepo;

	
	public List<Payment> findAll() {
		List<Payment> paymentList = (List<Payment>) paymentRepo.findAll();
		List<Payment> activePaymentList = new ArrayList();
		
		for (Payment payment: paymentList) {
			
			activePaymentList.add(payment);
		
	}
		
		return activePaymentList;
	}

	public List<Payment> listAll() {
		return (List<Payment>) paymentRepo.findAll(Sort.by("id").ascending());

	}

	public Page<Payment> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, PAYMENTS_PER_PAGE, sort);

		if (keyword != null) {
			return paymentRepo.findAll(keyword, pageable);
		}

		return paymentRepo.findAll(pageable);
	}

	public Payment save(Payment payment) {

		return paymentRepo.save(payment);

	}


	public Payment get(Integer id) throws PaymentNotFoundException {
		try {
			return paymentRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new PaymentNotFoundException("Could not find any Payment with ID " + id);
		}
	}

	public void delete(Integer id) throws PaymentNotFoundException {
		Long countById = paymentRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new PaymentNotFoundException("Could not find any Payment with ID " + id);
		}

		paymentRepo.deleteById(id);
	}
}
