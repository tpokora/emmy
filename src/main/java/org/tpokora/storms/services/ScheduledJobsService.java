package org.tpokora.storms.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("crone")
@Component
public class ScheduledJobsService {
}
