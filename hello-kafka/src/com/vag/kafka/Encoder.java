package com.vag.kafka;

import kafka.utils.VerifiableProperties;

public class Encoder extends kafka.serializer.StringEncoder{

	public Encoder(VerifiableProperties props) {
		super(props);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] toBytes(String s) {
		// TODO Auto-generated method stub
		return s.getBytes();
	}

	

}
